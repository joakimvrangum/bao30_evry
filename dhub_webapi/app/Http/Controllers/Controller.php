<?php

namespace App\Http\Controllers;

use App\Account;
use App\Card;
use App\Customer;
use App\Transaction;
use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Http\Request;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Support\Facades\Validator;

class Controller extends BaseController
{
    use AuthorizesRequests, DispatchesJobs, ValidatesRequests;

    public function index() {
        $stats = (['transaksjoner_antall'   =>  Transaction::all()->count(),
                    'kunder_antall'         =>  Customer::all()->count(),
                    'kontoer_antall'        =>  Account::all()->count(),
                    'bankkort_antall'       =>  Card::all()->count()
        ]);
        return view('welcome', compact('stats'));
    }

    public function newCardTransaction(Request $request) {
        $validator = Validator::make($request->input(),
            [
                'cardNr'            => 'required',
                'transactionAmount' => 'required',
            ],
            [
                'cardNr.required'	            =>	'Avvist! Mangler kortnummer',
                'transactionAmount.required'	=>	'Avvist! Mangler beløp for å trekke.',
            ]);

        if (!$validator->passes()) {
            return $validator->errors()->all();
        }

        error_log("Innkommende transaksjon fra kortnr: ".$request->cardNr);

        $card = Card::where('CARD_NR', '=', $request->cardNr)->first();
        if ($card == null) {
            return "Avvist! Kortnummeret tilhører ikke denne banken.";
        }

        if ($card->account->SALDO_VAL < $request->transactionAmount) {
            return "Avvist! Mangler dekning på konto.";
        }

        $transaction = new Transaction;
        $transaction->TRANSACTION_TEXT = $request->transactionText;
        $transaction->SALDO_VAL -= $request->transactionAmount;
        $transaction->BK_KONTO_NR = $card->account->BK_KONTO_NR;
        $transaction->KUNDE_NR = $card->account->KUNDE_NR;
        $transaction->save();

        $card->account->SALDO_VAL -= $request->transactionAmount;
        $card->push();

        return "TRANS_OK";
    }

}