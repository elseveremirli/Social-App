package com.elseveremirli.socialapp.services;

import com.elseveremirli.socialapp.dto.request.UserRequest;
import com.elseveremirli.socialapp.dto.response.UserResponse;
import com.elseveremirli.socialapp.entities.User;
import com.elseveremirli.socialapp.repository.UserRepository;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User user = UserRequest.convertUserRequestToUser(userRequest);
        User savedUser = userRepository.save(user);
        return UserResponse.convertUserToUserResponse(savedUser);
    }

    @Transactional
    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponse::convertUserToUserResponse).collect(Collectors.toList());
    }

    @Transactional
    public UserResponse getOneUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return UserResponse.convertUserToUserResponse(user);
    }
    @Transactional
    public UserResponse updateUserByUsername(String username, UserRequest userRequest) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        User requestUser = UserRequest.convertUserRequestToUser(userRequest);
        User user = updateUser(foundUser, requestUser);
        User savedUser = userRepository.save(user);
        return UserResponse.convertUserToUserResponse(savedUser);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    protected User updateUser(User foundUser, User requestUser) {
        Optional.ofNullable(requestUser.getName()).ifPresent(foundUser::setName);
        Optional.ofNullable(requestUser.getSurname()).ifPresent(foundUser::setSurname);
        Optional.ofNullable(requestUser.getBirthdate()).ifPresent(foundUser::setBirthdate);
        return foundUser;
    }

    public Boolean deleteUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
            userRepository.delete(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
