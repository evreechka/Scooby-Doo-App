package com.example.scoobydoo.utils;

import com.example.scoobydoo.entities.Profile;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ControllerUtils {
    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(collector);
        if (errors.get("ageError") != null) {
            errors.put("ageError", "age should be number between 1 and 119");
        }
        if (errors.get("weightError") != null) {
            errors.put("weightError", "weight should be float number more than 0.0");
        }
        if (errors.get("heightError") != null) {
            errors.put("heightError", "height should be float number more than 0.0");
        }
        if (errors.get("severityError") != null) {
            errors.put("severityError", "age should be number between 0 and 10");
        }
        if (errors.get("houseNumberError") != null) {
            errors.put("houseNumberError", "house number should be number more 0");
        }
        return errors;
    }
    public static boolean savePhoto(MultipartFile file, String uploadPath, Profile profile) {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
                uploadDir.mkdir();
            String uuid = java.util.UUID.randomUUID().toString();
            String resultFileName = uuid + "." + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + System.getProperty("file.separator") + resultFileName));
            } catch (IOException e) {
                return false;
            }
            profile.setPhoto(resultFileName);
        }
        return true;
    }
}
