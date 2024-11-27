package lotto.service;

import lotto.model.Lotto;
import lotto.model.LottoManager;
import lotto.model.LottoMember;
import lotto.utils.constants.LottoPrize;

import java.util.List;

public class LottoService {

    private LottoManager lottoManager;
    private LottoMember lottoMember;

    public void purchaseLotto(int price) {
        this.lottoManager = new LottoManager(price);
        this.lottoManager.purchaseLotto();
        this.lottoMember = new LottoMember();
        for (Lotto lotto : lottoManager.getPurchasedLotto()) {
            this.lottoMember.addPurchasedLotto(lotto);
        }
    }


    public Integer getTotalLotto() {
        return lottoManager.getTotalLotto();
    }

    public List<Lotto> getPurchasedLotto() {
        return lottoMember.getPurchasedLotto();
    }

    public void validateLotto(List<Integer> lottoNumbers, Integer bonusNumber) {
        lottoManager.validateLotto(lottoNumbers, bonusNumber);
    }

    public List<LottoPrize> isLottoResult() {
        List<LottoPrize> resultPrize = lottoManager.isLottoResult(lottoMember.getPurchasedLotto());
        lottoMember.setLottoResult(resultPrize);
        return resultPrize;
    }

    public void setLottoNumber(List<Integer> lottoNumbers, Integer bonusNumber) {
        lottoManager.setLottoNumbers(lottoNumbers);
        lottoManager.setLottoBonusNumber(bonusNumber);
    }

    public double resultProfitRate() {
        return lottoManager.resultProfitRate(lottoMember.getLottoResult());
    }
}
