package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.User;
import com.example.cruiseroyalebe.entity.UserImage;
import com.example.cruiseroyalebe.exception.NotFoundException;
import com.example.cruiseroyalebe.repository.UserImageRepository;
import com.example.cruiseroyalebe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserImageServiceImpl {

    private final UserImageRepository userImageRepository;

    private final UserRepository userRepository;

    public List<UserImage> getFilesOfCurrentUser(Integer userId) {
        return userImageRepository.findByUser_IdOrderByCreatedAtDesc(userId);
    }

    public UserImage uploadFile(MultipartFile file,Integer userId) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id " + userId));

        UserImage image = new UserImage();
        image.setType(file.getContentType());
        image.setData(file.getBytes());
        image.setUser(user);
        userImageRepository.save(image);

        return image;
    }

    public UserImage getFileById(Integer id) {
        return userImageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("File not found with id " + id));
    }

    public void deleteFile(Integer id) {
        UserImage file = getFileById(id);
        userImageRepository.delete(file);
    }
}
