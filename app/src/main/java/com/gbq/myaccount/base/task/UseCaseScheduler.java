package com.gbq.myaccount.base.task;

/**
 * Interface for schedulers, see {@link UseCaseThreadPoolScheduler}.
 */
interface UseCaseScheduler {

    void execute(Runnable runnable);

    <V extends UseCase.ResponseValue> void notifyResponse(final V response,
                                                          final UseCase.UseCaseCallback<V> useCaseCallback);

    <V extends UseCase.ResponseValue> void onError(final int code,
                                                   final UseCase.UseCaseCallback<V> useCaseCallback);
}
