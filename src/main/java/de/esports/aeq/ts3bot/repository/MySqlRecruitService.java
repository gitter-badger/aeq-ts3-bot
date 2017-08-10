/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.esports.aeq.ts3bot.repository;

import de.esports.aeq.ts3bot.core.api.VoteCount;
import de.esports.aeq.ts3bot.repository.api.RecruitService;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Lukas on 22.07.2017.
 */
public class MySqlRecruitService implements RecruitService {

    @Override
    public @NotNull Observable<Boolean> addPositiveVote(@Nullable String ts3id) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(true);
            observer.onComplete();
        });
    }

    @Override
    public @NotNull Observable<Boolean> addPositiveVotes(@Nullable String ts3id, int positiveVotes) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(true);
            observer.onComplete();
        });

    }

    @Override
    public @NotNull Observable<Boolean> addNegativeVote(@Nullable String ts3id) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(true);
            observer.onComplete();
        });
    }

    @Override
    public @NotNull Observable<Boolean> addNegativeVotes(@Nullable String ts3id, int negativeVotes) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(true);
            observer.onComplete();
        });
    }

    @Override
    public @NotNull Observable<VoteCount> getVoteCount(@Nullable String ts3id) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(null);
            observer.onComplete();
        });
    }
}
