<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Customer extends Model
{
    protected $table = 'CUSTOMER';
    const UPDATED_AT = 'LAST_MODIFIED';
    const CREATED_AT = null;
}
