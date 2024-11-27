package lotto.model;

import lotto.utils.validator.LottoValidator;
import lotto.utils.validator.Validator;

import java.util.Collections;
import java.util.List;

public class LottoWinningNumber {
    private final List<Integer> lottoNumbers;
    private final int bonusNumber;
    public Validator<List<Integer>> validator;

    public LottoWinningNumber(List<Integer> lottoNumbers, int bonusNumber) {
        this.validator = new LottoValidator();
        validator.validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
