package mapstruct.parser;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import mapstruct.car.WheelDto;
import mapstruct.car.Wheels;

import java.util.List;

@Mapper(config = MapperSpringConfig.class)
public interface WheelsDtoListMapper extends Converter<List<WheelDto>, Wheels> {
    @Override
    Wheels convert(List<WheelDto> source);
}