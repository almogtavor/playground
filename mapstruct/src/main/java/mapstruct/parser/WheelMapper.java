package mapstruct.parser;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import mapstruct.car.Wheel;
import mapstruct.car.WheelDto;

@Mapper(config = MapperSpringConfig.class)
public interface WheelMapper extends Converter<Wheel, WheelDto> {
    @Override
    WheelDto convert(Wheel source);
}