package com.github.sequence;

import com.github.sequence.common.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}
