package org.acme.annotation.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Completion;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;

public class ExtensionAnnotationProcessor extends AbstractProcessor {


    @Override
    public Set<String> getSupportedOptions() {
        return Collections.emptySet();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of("org.acme.annotations.BuildStep");
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            final Filer filer = processingEnv.getFiler();

            URI uri = null;
            try {
                FileObject tempResource = filer.createResource(StandardLocation.SOURCE_OUTPUT, "", "ignore.tmp");
                uri = tempResource.toUri();
                Paths.get(uri)
                     .getParent();
            } catch (RuntimeException e) {
                processingEnv.getMessager()
                             .printMessage(Diagnostic.Kind.ERROR, "Resource path URI is invalid: " + uri + "\nRoot error is " + e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

        return true;

    }

    @Override
    public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation, ExecutableElement member,
                                                         String userText) {
        return Collections.emptySet();
    }

}
