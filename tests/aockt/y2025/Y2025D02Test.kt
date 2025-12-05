package aockt.y2025

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2025, 2, "Gift Shop")
class Y2025D02Test: AdventSpec<Y2025D02>({
    partOne(enabled = true) {
        "11-22" shouldOutput 33
        "95-115" shouldOutput 99
        "998-1012" shouldOutput 1010
        "1188511880-1188511890" shouldOutput 1188511885
        "222220-222224" shouldOutput 222222
        "1698522-1698528" shouldOutput 0
        "446443-446449" shouldOutput 446446
        "38593856-38593862" shouldOutput 38593859
        "565653-565659" shouldOutput 0
        "824824821-824824827" shouldOutput 0
        "2121212118-2121212124" shouldOutput 0
        "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124" shouldOutput 1227775554
    }

    partTwo(enabled = true) {
        "11-22" shouldOutput 33
        "95-115" shouldOutput 99 + 111
        "998-1012" shouldOutput 999 + 1010
        "1188511880-1188511890" shouldOutput 1188511885
        "222220-222224" shouldOutput 222222
        "1698522-1698528" shouldOutput 0
        "446443-446449" shouldOutput 446446
        "38593856-38593862" shouldOutput 38593859
        "565653-565659" shouldOutput 565656
        "824824821-824824827" shouldOutput 824824824
        "2121212118-2121212124" shouldOutput 2121212121
        "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124" shouldOutput 4174379265
    }
})