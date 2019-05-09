<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Card extends Model
{
    protected $table = 'ACC_CARD';

    public function account() {
        return $this->hasOne('App\Account', 'BK_KONTO_NR', 'BK_KONTO_NR');
    }
}
