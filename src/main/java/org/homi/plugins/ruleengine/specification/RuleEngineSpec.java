package org.homi.plugins.ruleengine.specification;

import static org.homi.plugin.specification.Constraints.*;
import static org.homi.plugin.specification.SpecificationHelper.defineType;
import static org.homi.plugin.specification.SpecificationHelper.processType;
import static org.homi.plugin.specification.SpecificationHelper.processTypes;

import java.util.List;
import java.util.Map;

import org.homi.plugin.specification.ISpecification;
import org.homi.plugin.specification.SpecificationID;
import org.homi.plugin.specification.types.TypeDef;

class Types {
	public static TypeDef<?> RULE_TEXT= defineType(String.class, notNull(), minLength(1)); 
	public static TypeDef<?> RULE_RESULT= defineType(Map.class, notNull()); 
	public static TypeDef<?> RULE_ID= defineType(Integer.class, notNull()); 
	public static TypeDef<?> SUCCESS= defineType(Boolean.class, notNull()); 
}

@SpecificationID(id="RuleEngineSpec")
public enum RuleEngineSpec implements ISpecification {
	ADD_RULE(Types.SUCCESS, Types.RULE_TEXT),
	GET_RULES(Types.RULE_RESULT),
	REMOVE_RULE(Types.SUCCESS, Types.RULE_ID);
	
	private List<TypeDef<?>> parameterTypes;
	private TypeDef<?> returnType;
	
	RuleEngineSpec(Object returnType, Object...parameterTypes ) {
		try {
			this.returnType = processType(returnType);
			this.parameterTypes = processTypes(parameterTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<TypeDef<?>> getParameterTypes() {
		return this.parameterTypes;
	}
	
	@Override
	public TypeDef<?> getReturnType() {
		return this.returnType;
	}
}