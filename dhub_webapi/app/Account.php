<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Account extends Model
{
    protected $table = 'ACCOUNT';
    protected $primaryKey = 'BK_KONTO_NR';
    const UPDATED_AT = 'LAST_MODIFIED';
    const CREATED_AT = null;

}
