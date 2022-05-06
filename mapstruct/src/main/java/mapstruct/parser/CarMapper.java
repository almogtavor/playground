package mapstruct.parser;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;
import mapstruct.car.Car;
import mapstruct.car.CarDto;

@Mapper(config = MapperSpringConfig.class)
public interface CarMapper extends Converter<Car, CarDto> {
    @Mapping(target = "seats", source = "seatConfiguration")
    CarDto convert(Car car);
}