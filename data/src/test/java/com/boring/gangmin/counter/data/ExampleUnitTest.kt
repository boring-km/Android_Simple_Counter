package com.boring.gangmin.counter.data

import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {

    // 이미 있는 코드를 가지고 테스트 코드를 작성할 때는 구조만 잘 잡혀 있으면 빠르게 개발이 가능하다.
    @Test
    fun 처음_카운터의_값은_0이다() {
        val repository = CounterNumberRepository()

        assertEquals(0, repository.counterNumber.value)
    }

    @Test
    fun `add()_함수를_3번_실행하면_text_값은_3이다`() {
        val repository = CounterNumberRepository()
        repository.add()
        repository.add()
        repository.add()
        assertEquals(3, repository.counterNumber.value)
    }

    // 하지만 새로운 기능을 추가하는 TDD를 수행한다면?
}