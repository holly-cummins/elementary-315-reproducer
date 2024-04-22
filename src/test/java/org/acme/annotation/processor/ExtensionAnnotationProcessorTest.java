package org.acme.annotation.processor;

import com.karuslabs.elementary.Results;
import com.karuslabs.elementary.junit.JavacExtension;
import com.karuslabs.elementary.junit.annotations.Classpath;
import com.karuslabs.elementary.junit.annotations.Processors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(JavacExtension.class)
@Processors({ExtensionAnnotationProcessor.class})
class ExtensionAnnotationProcessorTest {

    @BeforeEach
    void beforeEach() {
        // This is of limited use, since the filesystem doesn't seem to directly generate files, in the current usage
        //   CustomMemoryFileSystemProvider.reset();
    }

    @Test
    @Classpath("org.acme.examples.ClassWithBuildStep")
    void shouldProcessClassWithBuildStepWithoutErrors(Results results) throws IOException {
        assertNoErrrors(results);
    }

    private static void assertNoErrrors(Results results) {
        assertEquals(0, results.find()
                               .errors()
                               .count(),
                "Errors were: " + results.find()
                                         .errors()
                                         .diagnostics());
    }
}
