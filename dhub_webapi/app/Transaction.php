<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Transaction extends Model
{
    protected $table = 'ACC_TRANS';
    const UPDATED_AT = 'LAST_MODIFIED';
    const CREATED_AT = null;
}
