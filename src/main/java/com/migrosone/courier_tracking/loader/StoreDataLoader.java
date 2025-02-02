package com.migrosone.courier_tracking.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migrosone.courier_tracking.entity.Store;
import com.migrosone.courier_tracking.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StoreDataLoader implements CommandLineRunner {

    private static final String PATH = "/store.json";

    private final StoreRepository storeRepository;

    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) {
        try (var inputStream = StoreDataLoader.class.getResourceAsStream(PATH)) {
            final List<Store> stores = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            storeRepository.saveAll(stores);
            log.info("Store data created successfully");
        } catch (Exception e) {
            log.error("Error while loading store data", e);
        }
    }
}
