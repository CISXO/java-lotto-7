package lotto.controller;

import lotto.view.MemberInputView;
import lotto.view.OutputView;

public class LottoController {

    private final MemberInputView memberInputView;
    private final OutputView outputView;

    public LottoController() {
        this.memberInputView = new MemberInputView();
        this.outputView = new OutputView();
    }

    public void run() {
        int price = memberInputView.getPrice();

    }
}
