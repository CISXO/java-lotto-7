package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.utils.constants.LottoConstants;
import lotto.utils.constants.LottoPrize;
import lotto.utils.validator.LottoValidator;
import lotto.utils.validator.Validator;

import java.util.ArrayList;
import java.util.List;


public class LottoManager {
    private final Integer price;
    private final List<Lotto> purchasedLotto = new ArrayList<>();
    public Validator<List<Integer>> validator;
    private int totalLotto;

    private LottoWinningNumber winningNumber;

    public LottoManager(Integer price) {
        this.price = price;
        this.validator = new LottoValidator();
    }

    public void purchaseLotto() {
        totalLotto = price / LottoConstants.LOTTO_PRICE;
        for (int i = 0; i < totalLotto; i++) {
            purchasedLotto.add(generateRandomLotto());
        }
    }

    public Integer getTotalLotto() {
        return totalLotto;
    }

    public List<Lotto> getPurchasedLotto() {
        return purchasedLotto;
    }

    private Lotto generateRandomLotto() {
        List<Integer> randomLottoNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                .stream()
                .sorted()
                .toList();
        return new Lotto(randomLottoNumber);
    }

    public List<LottoPrize> isLottoResult(List<Lotto> purchasedLotto) {
        List<LottoPrize> prizeResults = new ArrayList<>();

        for (Lotto lotto : purchasedLotto) {
            int matchCount = calculateMatchCount(lotto, winningNumber.getNumbers());
            boolean bonusMatch = isBonusMatched(lotto, winningNumber.getBonusNumber());
            LottoPrize prize = determinePrize(matchCount, bonusMatch);
            prizeResults.add(prize);
        }

        return prizeResults;
    }

    private int calculateMatchCount(Lotto lotto, List<Integer> lottoResult) {
        return (int) lotto.getLottoNumbers().stream()
                .filter(lottoResult::contains)
                .count();
    }

    private boolean isBonusMatched(Lotto lotto, Integer bonusNumber) {
        return lotto.getLottoNumbers().contains(bonusNumber);
    }

    private LottoPrize determinePrize(int matchCount, boolean bonusMatch) {
        return LottoPrize.findPrize(matchCount, bonusMatch);
    }

    public double resultProfitRate(List<LottoPrize> lottoResult) {
        int totalInvested = lottoResult.size() * LottoConstants.LOTTO_PRICE;
        int totalEarnings = 0;

        for (LottoPrize prize : lottoResult) {
            totalEarnings += prize.getLottoPrize();
        }

        if (totalInvested == 0) { return 0.0;}

        return (double) totalEarnings / totalInvested * 100;
    }

    public void saveWinningNumbers(List<Integer> lottoNumbers, Integer bonusNumber) {
        this.winningNumber = new LottoWinningNumber(lottoNumbers, bonusNumber);
    }

    public boolean validateLotto(List<Integer> lottoNumbers, Integer bonusNumber) {
        try {
            validator.validate(lottoNumbers);
            LottoValidator lottoValidator = new LottoValidator();
            lottoValidator.validateBonusNumber(bonusNumber, lottoNumbers);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
