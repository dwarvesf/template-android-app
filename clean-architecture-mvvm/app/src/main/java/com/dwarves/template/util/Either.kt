package {{packageName}}.util

@Suppress("unused")
sealed class Either<E, V> {
    data class Error<E, V>(val error: E) : Either<E, V>()
    data class Value<E, V>(val value: V) : Either<E, V>()

    companion object {
        fun <E, V> value(value: V): Either<E, V> = Either.Value(value)

        fun <E, V> error(value: E): Either<E, V> = Either.Error(value)
    }
}
