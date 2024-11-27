package lotto.controller;

import lotto.service.LottoService;
import lotto.utils.constants.LottoPrize;
import lotto.view.input.ManagerInputView;
import lotto.view.input.MemberInputView;
import lotto.view.output.OutputView;

import java.util.List;

public class LottoController {
    private final MemberInputView memberInputView;
    private final ManagerInputView managerInputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController() {
        this.memberInputView = new MemberInputView();
        this.managerInputView = new ManagerInputView();
        this.outputView = new OutputView();
        this.lottoService = new LottoService();
    }

    public void run() {
        process(this::getPriceFromUser);
        process(this::purchaseLotto);
        process(this::displayPurchaseInfo);
        process(this::getLottoNumber);
        process(this::displayResultStatistics);
    }

    private void getPriceFromUser() {
        outputView.printGetPrice();
    }

    private void purchaseLotto() {
        lottoService.purchaseLotto(memberInputView.getPrice());
    }

    private void displayPurchaseInfo() {
        outputView.printPurchasedLottoCount(lottoService.getTotalLotto());
        outputView.printLottoPurchaseList(lottoService.getPurchasedLotto());
    }

    private void getLottoNumber() {
        outputView.printLottoNumber();
        List<Integer> lottoNumbers = managerInputView.getLottoNumbers();
        outputView.printLottoBonusNumber();
        Integer bonusNumber = managerInputView.getLottoBonusNumber();
        lottoService.validateLotto(lottoNumbers, bonusNumber);
        lottoService.setLottoNumber(lottoNumbers, bonusNumber);
    }

    private void displayResultStatistics() {
        List<LottoPrize> resultPrize = lottoService.isLottoResult();
        double resultProfitRate = lottoService.resultProfitRate();
        outputView.resultStatistics(resultPrize, resultProfitRate);
    }

    private void process(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            outputView.exception(e);
            action.run();
            throw e;
        }
    }
}
