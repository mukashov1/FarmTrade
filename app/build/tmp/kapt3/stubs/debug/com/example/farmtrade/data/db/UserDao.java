package com.example.farmtrade.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\n"}, d2 = {"Lcom/example/farmtrade/data/db/UserDao;", "", "findSignedInUser", "Lcom/example/farmtrade/data/db/User;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUser", "insertUser", "", "user", "(Lcom/example/farmtrade/data/db/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface UserDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertUser(@org.jetbrains.annotations.NotNull
    com.example.farmtrade.data.db.User user, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUser(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.farmtrade.data.db.User> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE isSigned = 1 LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object findSignedInUser(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.farmtrade.data.db.User> $completion);
}