package com.Vinh.CompileAnnotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

@SupportedAnnotationTypes(value = { "com.Vinh.CompileAnnotation.PublicFinal" })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class PublicFinalProcessor extends AbstractProcessor {

	private Messager messager;

	@Override
	public void init(ProcessingEnvironment env) {
		messager = env.getMessager();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
		for (TypeElement ann : annotations) {
			Set<? extends Element> e2s = env.getElementsAnnotatedWith(ann);
			for (Element e2 : e2s) {
				Set<Modifier> modifiers = e2.getModifiers();
				if (!(modifiers.contains(Modifier.FINAL) && modifiers.contains(Modifier.PUBLIC))) {
					messager.printMessage(Kind.ERROR, "Method/field wasn't public and final", e2);
				}
			}
		}
		return true;
	}
}