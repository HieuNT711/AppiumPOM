package com.utils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import okhttp3.Request;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectMapperUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private ObjectMapperUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Gson getGsonInstance() {
        return (new GsonBuilder())
                .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .disableHtmlEscaping()
                .create();
    }

    public static ObjectMapper getMapperInstance() {
        return new ObjectMapper();
    }

    public static <T> T convertJSONStringToDTOClassByObjectMapper(
            String jsonString, Class<T> clazz) {
        try {
            ObjectMapper mapper = getMapperInstance();
            mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            return mapper.readValue(jsonString, clazz);
        } catch (IOException var4) {
            String className = clazz.getSimpleName();
            throw new RuntimeException(
                    String.format(
                            "Error in mapping to response [%s] class. %s",
                            className, var4.getMessage()),
                    var4);
        }
    }

    public static String dtoWriteValueAsString(Object object) throws JsonProcessingException {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (Throwable var2) {
            throw var2;
        }
    }

    public static String convertDTOClassToJSONString(Object dtoObject) {
        try {
            Gson builder = getGsonInstance();
            return builder.toJson(dtoObject);
        } catch (JsonIOException | UnsupportedOperationException | IllegalArgumentException var4) {
            try {
                return getMapperInstance()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(dtoObject);
            } catch (JsonProcessingException var3) {
                throw new RuntimeException("Error in convert DTO to JSON String", var3);
            }
        }
    }

    public static <T extends Request> T createDTOObjectByDataTable(
            Class<T> dtoClass, Map<String, String> dataTable) {
        T dtoClassInstance;
        try {
            dtoClassInstance = dtoClass.getDeclaredConstructor().newInstance();
            List<Class<?>> classes = ReflectionUtils.getAllClasses(dtoClassInstance);
            List<Field> allFields = ReflectionUtils.getAllFields(classes);
            Map<String, Field> fields =
                    allFields.stream()
                            .collect(
                                    Collectors.toMap(
                                            f -> f.getName().toLowerCase(),
                                            f -> f,
                                            (field1, field2) -> field1));
            for (Map.Entry<String, String> param : dataTable.entrySet()) {
                String paramKey = param.getKey();
                String paramValue = param.getValue();
                if (fields.containsKey(paramKey.toLowerCase())) {
                    Field paramFiled = fields.get(paramKey.toLowerCase());
                    paramFiled.setAccessible(true);
                    paramFiled.set(dtoClassInstance, paramValue);
                }
            }
            return dtoClassInstance;
        } catch (InstantiationException
                | IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException
                | NoSuchMethodException
                | SecurityException e) {
            String className = dtoClass.getSimpleName();
            throw new RuntimeException(
                    String.format(
                            "Error in mapping DTO class [%s] to request params. %s",
                            className, e.getMessage()),
                    e);
        }
    }
}
