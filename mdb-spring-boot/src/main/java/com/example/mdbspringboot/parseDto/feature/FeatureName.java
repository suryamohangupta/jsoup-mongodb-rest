package com.example.mdbspringboot.parseDto.feature;


public enum FeatureName {
    SAFETY("Safety"),
    BRACKING_AND_TRACTION("Braking & Traction"),
    LOCKS_AND_SECURITY("Locks & Security"),
    COMFORT_AND_CONVENIENCE("Comfort & Convenience"),
    SEATS_AND_UPHOLSTERY("Seats & Upholstery"),
    STORAGE("Storage"),
    DOORS_WINDOWS_MIRRORS_AND_WIPERS("Doors, Windows, Mirrors & Wipers"),
    EXTERIOR("Exterior"),
    LIGHTING("Lighting"),
    INSTRUMENTATION("Instrumentation"),
    ENTERTAINMENT_INFORMATION_COMMUNICATION("Entertainment, Information & Communication"),
    OTHERS("Others");

    private final String featureName;

    FeatureName(String featureName)
    {
        this.featureName = featureName;
    }

    public static FeatureName getFeatureNameFromString(String featureName)
    {
        for (FeatureName featureName1 : FeatureName.values())
        {
            if(featureName1.featureName.equals(featureName))
            {
                return featureName1;
            }
        }
        return FeatureName.OTHERS;
    }
}
