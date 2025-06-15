package com.ram.Springboot.tutorial.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Endpoint(id="features")
public class FeatureEndPoint {

    private final Map<String, Feature> featureMap=new ConcurrentHashMap<>();

    public FeatureEndPoint()
    {
        featureMap.put("Department",new Feature(true));
    }

    @ReadOperation
    public Map<String,Feature> features()
    {
        return featureMap;
    }

    @ReadOperation
    public Feature feature (@Selector String fetureName)
    {
        return featureMap.get(fetureName);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class Feature {
        private boolean isEnabled;
    }
}
