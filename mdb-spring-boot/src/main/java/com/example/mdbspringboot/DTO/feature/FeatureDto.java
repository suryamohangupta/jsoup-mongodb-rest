package com.example.mdbspringboot.DTO.feature;

import com.example.mdbspringboot.DTO.feature.FeatureName;
import lombok.Data;

import java.util.List;

@Data
public class FeatureDto {

    FeatureName featureName;
    List<String> featureDetails;
}
