package real.world.data.pipelines.parser;

import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;
import real.world.data.pipelines.model.ExampleData;
import real.world.data.pipelines.model.ExampleEvent;
import real.world.data.pipelines.s3.wrapper.MapperSpringConfig;

@Mapper(config = MapperSpringConfig.class)
public interface ExampleMapper extends Converter<ExampleData, ExampleEvent> {
    ExampleEvent convert(@NotNull ExampleData exampleData);
}