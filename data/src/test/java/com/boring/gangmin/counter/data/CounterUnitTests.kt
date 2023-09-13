package com.boring.gangmin.counter.data

import org.junit.Assert.assertEquals
import org.junit.Test

class CounterUnitTests {

    // 이미 있는 코드를 가지고 테스트 코드를 작성할 때는 구조만 잘 잡혀 있으면 빠르게 개발이 가능하다.
    @Test
    fun 처음_카운터의_값은_0이다() {
        val repository = CounterNumberRepository()

        assertEquals(0, repository.counterNumber.value)
    }

    @Test
    fun `add()_함수를_3번_실행하면_counterNumber_값은_3이다`() {
        val repository = CounterNumberRepository()
        repository.add()
        repository.add()
        repository.add()
        assertEquals(3, repository.counterNumber.value)
    }

    @Test
    fun `setValue()_함수로_"10"_문자열을_넣으면_counterNumber_값이_10으로_나온다`() {
        val repository = CounterNumberRepository()
        repository.setValue("10")

        assertEquals(10, repository.counterNumber.value)
    }

    @Test
    fun `minus()_함수를_실행하면_counterNumber_값은_10에서_9가_된다`() {
        val repository = CounterNumberRepository()
        repository.setValue("10")
        assertEquals(10, repository.counterNumber.value)

        repository.minus()
        assertEquals(9, repository.counterNumber.value)
    }

    @Test
    fun text가_1000인_상태에서_reset하면_counterNumber_값은_0이_된다() {
        val repository = CounterNumberRepository()
        repository.setValue("1000")
        assertEquals(1000, repository.counterNumber.value)

        repository.reset()
        assertEquals(0, repository.counterNumber.value)
    }
}