package com.github.sequence.segment.dao;

import com.github.sequence.segment.model.Sequence;

import java.util.List;

public interface SequenceDao {

     List<Sequence> getAllSequence();
     Sequence updateMaxIdAndGetSequence(String tag);
     Sequence updateMaxIdByCustomStepAndGetSequence(Sequence sequence);
     List<String> getAllTags();
}
