package com.khramykh.store.service;

import com.khramykh.store.repository.PartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartsService {

    @Autowired
    private PartRepo partRepo;


}
