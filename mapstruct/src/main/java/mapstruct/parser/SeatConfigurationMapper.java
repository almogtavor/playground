package mapstruct.parser;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;
import mapstruct.car.SeatConfiguration;
import mapstruct.car.SeatConfigurationDto;

@Mapper(config = MapperSpringConfig.class)
public interface SeatConfigurationMapper extends Converter<SeatConfiguration, SeatConfigurationDto> {
    @Mapping(target = "seatCount", source = "numberOfSeats")
    @Mapping(target = "material", source = "seatMaterial")
    SeatConfigurationDto convert(SeatConfiguration seatConfiguration);
}