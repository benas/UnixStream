package io.github.benas.unixstream.components;

import io.github.benas.unixstream.Predicates;
import io.github.benas.unixstream.UnixStream;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.benas.unixstream.Predicates.contains;
import static org.assertj.core.api.Assertions.assertThat;

public class ExcludeTest {

    private Stream<String> stream;

    private Exclude<String> exclude;

    @Before
    public void setUp() {
        stream = Stream.of("a", "ab", "bc");
        exclude = Exclude.exclude(contains("a"));
    }

    @Test
    public void apply() {
        List<String> strings = exclude.apply(stream).collect(Collectors.toList());

        assertThat(strings).isNotEmpty().hasSize(1).containsExactly("bc");
    }

    @Test
    public void exclude() throws Exception {
        UnixStream<String> unixStream = UnixStream.from(stream).exclude(Predicates.contains("a"));

        assertThat(unixStream).containsExactly("bc");
    }
}
