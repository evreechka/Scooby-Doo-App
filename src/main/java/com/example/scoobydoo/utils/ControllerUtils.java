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
        return bindingResult.getFieldErrors().stream().collect(collector);
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
