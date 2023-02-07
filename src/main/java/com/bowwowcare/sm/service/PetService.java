package com.bowwowcare.sm.service;

import com.bowwowcare.sm.domain.pet.PetRepository;
import com.bowwowcare.sm.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;
}
