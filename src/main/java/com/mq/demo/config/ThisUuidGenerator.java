package com.mq.demo.config;

import org.flowable.common.engine.impl.cfg.IdGenerator;

import java.util.UUID;

public class ThisUuidGenerator implements IdGenerator {
    @Override
    public String getNextId() {
        return "ceh-" + UUID.randomUUID().toString();
    }
}
