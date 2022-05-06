package mapstruct.parser;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import mapstruct.car.Wheel;
import mapstruct.car.WheelDto;
import mapstruct.car.Wheels;

import java.util.List;

@Mapper(config = MapperSpringConfig.class, imports = Wheel.class)
public interface WheelsMapper extends Converter<Wheels, List<WheelDto>> {
    @Override
    List<WheelDto> convert(Wheels source);
}