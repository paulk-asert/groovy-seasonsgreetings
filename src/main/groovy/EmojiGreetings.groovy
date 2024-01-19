
assert '🎄🤶🎅'.codePoints().allMatch(Character::isEmoji)

assert '🎄🤶🎅' ==~ /\p{IsEmoji}{3}/

assert '🎄🤶🎅'.codePoints.collect(Character::isEmojiModifierBase) == [false, true, true]

assert '🏻🏼🏽🏾🏿'.codePoints().allMatch(Character::isEmojiModifier)

assert '🎅🏿'.codePoints == [*'🎅'.codePoints, *'🏿'.codePoints]
assert '🎅🏾' ==~ /\p{IsEmoji_Modifier_Base}\p{IsEmoji_Modifier}/

var tonedSantas = ['🎅🏻','🎅🏼','🎅🏽','🎅🏾','🎅🏿']
var skinTones = ['🏻','🏼','🏽','🏾','🏿']
assert tonedSantas == skinTones.collectMany{ ['🎅' + it] }

assert '🧑‍🎄'.codePoints == [*'🧑'.codePoints, *'‍'.codePoints, *'🎄'.codePoints]
assert '🧑‍🎄' == /\uD83E\uDDD1\u200d\uD83C\uDF84/
assert '🧑‍🎄' ==~ /\x{1f9d1}\x{200d}\x{1f384}/
assert '🧑‍🎄' ==~ /\N{ADULT}\N{ZERO WIDTH JOINER}\N{CHRISTMAS TREE}/

assert '👨‍👩‍👧'.codePoints == ['👨', '‍', '👩', '‍', '👧']*.codePoints.flatten()
assert '👨‍👩‍👧' ==~ /\x{1f468}\x{200d}\x{1f469}\x{200d}\x{1f467}/
assert '👨‍👩‍👧' ==~ /\N{MAN}\N{ZERO WIDTH JOINER}\N{WOMAN}\N{ZERO WIDTH JOINER}\N{GIRL}/

assert '🙋🏻‍♀️'.codePoints == ['🙋🏻', '‍', '♀️']*.codePoints.flatten()
assert '🙋🏻‍♀️'.codePoints.size() == 5
assert '🙋🏻‍♀️'.codePoints.eachWithIndex { cp, idx ->
    switch(idx) {
        case   0 -> assert Character.isEmojiModifierBase(cp)
        case   1 -> assert Character.isEmojiModifier(cp)
        case 2,4 -> assert Character.isEmojiComponent(cp)
        case   3 -> assert Character.isExtendedPictographic(cp)
    }
}

assert '🙋🏻‍♀️' ==~ /\x{1f64b}\x{1f3fb}\x{200d}\x{2640}\x{fe0f}/
assert '🙋🏻‍♀️' ==~ /(?x)
                 \N{HAPPY PERSON RAISING ONE HAND}
                 \N{EMOJI MODIFIER FITZPATRICK TYPE-1-2}
                 \N{ZERO WIDTH JOINER}
                 \N{FEMALE SIGN}
                 \N{VARIATION SELECTOR-16}/
assert '🙋🏻‍♀️' ==~ /(?x)
                 \p{IsEmoji_Modifier_Base}
                 \p{IsEmoji_Modifier}
                 \p{IsEmoji_Component}
                 \p{IsExtended_Pictographic}
                 \p{IsEmoji_Component}/

