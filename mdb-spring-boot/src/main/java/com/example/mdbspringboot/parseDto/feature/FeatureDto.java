package com.example.mdbspringboot.parseDto.feature;

import lombok.Data;

import java.util.List;

@Data
public class FeatureDto {

    FeatureName featureName;
    List<String> featureDetails;
}
