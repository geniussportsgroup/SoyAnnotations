package com.geniussports.soy.meta;

import javax.annotation.concurrent.Immutable;
import java.lang.reflect.Member;
import java.util.function.Function;

@Immutable
public class MetaMemberInformation extends MetaValueConvertableInformation {

    private final Member member;

    public MetaMemberInformation(Boolean dynamic,
                                 Class<?> type,
                                 Function<Object, ?> valueConverter,
                                 Member member) {
        super(dynamic, type, valueConverter);
        this.member = member;
    }

    public final Member getMember() {
        return member;
    }
}
