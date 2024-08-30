package com.coding.project.uber.uberApp.config;

import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coding.project.uber.uberApp.dto.PointDto;
import com.coding.project.uber.uberApp.utils.GeomatryUtil;

@Configuration
public class MapperConfig {

    @Bean
        public ModelMapper getModelMapper() {

            ModelMapper modelMapper = new ModelMapper(); 
            modelMapper.typeMap(PointDto.class,Point.class).setConverter(Context ->{
                PointDto pointDto = Context.getSource();
                return GeomatryUtil.CreatePoint(pointDto);
            });

            modelMapper.typeMap(Point.class, PointDto.class).setConverter(context -> {
                Point point = context.getSource();
                double[] coordianates = {
                    point.getX(),
                    point.getY()
                };
                return new PointDto(coordianates);
            } );


        return modelMapper;

    }

}
