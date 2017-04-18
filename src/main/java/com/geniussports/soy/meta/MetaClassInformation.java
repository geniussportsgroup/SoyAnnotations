package com.geniussports.soy.meta;

import javax.annotation.concurrent.Immutable;
import java.util.Map;
import java.util.function.Function;

@Immutable
public class MetaClassInformation extends MetaValueConvertableInformation {

    private final Map<String, MetaMemberInformation> membersInformation;

    private final Boolean useOriginalToString;

    public MetaClassInformation(Boolean dynamic,
                                Class<?> type,
                                Function<Object, ?> valueConverter,
                                Map<String, MetaMemberInformation> membersInformation,
                                Boolean useOriginalToString) {
        super(dynamic, type, valueConverter);
        this.membersInformation = membersInformation;
        this.useOriginalToString = useOriginalToString;
    }

    public final Map<String, MetaMemberInformation> getMembersInformation() {
        return membersInformation;
    }

    public final Boolean getUseOriginalToString() {
        return useOriginalToString;
    }
}
