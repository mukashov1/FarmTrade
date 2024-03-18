package com.example.farmtrade.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/example/farmtrade/data/repository/UserRepository;", "", "userDao", "Lcom/example/farmtrade/data/db/UserDao;", "(Lcom/example/farmtrade/data/db/UserDao;)V", "findSignedInUser", "Lcom/example/farmtrade/data/db/User;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUser", "insertUser", "", "user", "(Lcom/example/farmtrade/data/db/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UserRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.farmtrade.data.db.UserDao userDao = null;
    
    public UserRepository(@org.jetbrains.annotations.NotNull
    com.example.farmtrade.data.db.UserDao userDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object insertUser(@org.jetbrains.annotations.NotNull
    com.example.farmtrade.data.db.User user, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUser(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.farmtrade.data.db.User> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object findSignedInUser(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.farmtrade.data.db.User> $completion) {
        return null;
    }
}