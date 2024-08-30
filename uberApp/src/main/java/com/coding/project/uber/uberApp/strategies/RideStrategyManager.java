package com.coding.project.uber.uberApp.strategies;

import java.time.LocalTime;
import org.springframework.stereotype.Component;
import com.coding.project.uber.uberApp.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.coding.project.uber.uberApp.strategies.impl.DriverMathcingHighestRatingStrategy;
import com.coding.project.uber.uberApp.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.coding.project.uber.uberApp.strategies.impl.RideFareSurgePricingFareCalclucationStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;
    private final DriverMathcingHighestRatingStrategy driverMathcingHighestRatingStrategy;
    private final RideFareDefaultFareCalculationStrategy rideFareDefaultFareCalculationStrategy;
    private final RideFareSurgePricingFareCalclucationStrategy rideFareSurgePricingFareCalclucationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double RiderRating) {
        if (RiderRating >= 4.8) {
            return driverMathcingHighestRatingStrategy;
        } else {
            return driverMatchingNearestDriverStrategy;
        }
    };

    public RideFareCalculationStrategy rideFareCalculationStrategy() {

        LocalTime SurgerStartTime = LocalTime.of(18, 0, 0);

        LocalTime SurgerEndTime = LocalTime.of(21, 0, 0);

        LocalTime currenTime = LocalTime.now();

        boolean isSurgerTime;

        if (currenTime.isAfter(SurgerStartTime) && currenTime.isBefore(SurgerEndTime)) {
            isSurgerTime = true;
        } else {
            isSurgerTime = false;
        }

        if (isSurgerTime) {
            return rideFareSurgePricingFareCalclucationStrategy;
        } else {
            return rideFareDefaultFareCalculationStrategy;
        }
    }

}
