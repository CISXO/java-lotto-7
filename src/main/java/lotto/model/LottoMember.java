package lotto.model;

import lotto.utils.constants.LottoPrize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMember {

    private final List<Lotto> purchasedLotto;
    private final List<LottoPrize> lottoResult;
    List<Integer> purchasedLottoNumbers = new ArrayList<>();

    public LottoMember() {
        this.purchasedLotto = new ArrayList<>();
        this.lottoResult = new ArrayList<>();
    }

    public List<Lotto> getPurchasedLotto() {
        for (Lotto lotto : purchasedLotto) {
            purchasedLottoNumbers.addAll(lotto.getLottoNumbers());
        }
        return Collections.unmodifiableList(purchasedLotto);
    }

    public void addPurchasedLotto(Lotto lotto) {
        purchasedLotto.add(lotto);
    }

    public void setLottoResult(List<LottoPrize> results) {
        lottoResult.clear();
        lottoResult.addAll(results);
    }

    public List<LottoPrize> getLottoResult() {
        return Collections.unmodifiableList(lottoResult);
    }
}
