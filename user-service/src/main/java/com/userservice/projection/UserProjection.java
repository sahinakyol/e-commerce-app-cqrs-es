package com.userservice.projection;

import com.userservice.event.UserCreatedEvent;
import com.userservice.model.UserModel;
import com.userservice.query.GetUsersQuery;
import com.userservice.repository.UserProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserProjection {
    private final UserProjectionRepository userProjectionRepository;

    @EventHandler
    public void on(UserCreatedEvent userCreatedEvent) {
        UserModel userModel = new UserModel(
                userCreatedEvent.getId(),
                userCreatedEvent.getName()
        );
        userProjectionRepository.save(userModel);
        sendCreateAccountEvent(userCreatedEvent.getId());
    }

    @QueryHandler
    public List<UserModel> handle(GetUsersQuery getUsersQuery) {
        return userProjectionRepository.findAll();
    }

    private void sendCreateAccountEvent(String id) {
        /* TODO */
    }
}
