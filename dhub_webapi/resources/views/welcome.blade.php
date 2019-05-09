<!doctype html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>dHub</title>

        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="{{ asset('css/semantic.min.css') }}">
    </head>
    <body>
        <h1>dHub</h1>

        <div class="ui four statistics segment">
          <div class="statistic">
            <div class="value">
                <i class="shopping cart icon"></i>
              {{ $stats['transaksjoner_antall'] }}
            </div>
            <div class="label">
              Transaksjoner
            </div>
          </div>
          <div class="statistic">
            <div class="value">
                <i class="users icon"></i>
              {{ $stats['kunder_antall'] }}
            </div>
            <div class="label">
              Kunder
            </div>
          </div>
          <div class="statistic">
            <div class="value">
              <i class="money bill alternate icon"></i>
                {{ $stats['kontoer_antall'] }}
            </div>
            <div class="label">
              Kontoer
            </div>
          </div>
          <div class="statistic">
            <div class="value">
              <i class="credit card outline icon"></i>
                {{ $stats['bankkort_antall'] }}
            </div>
            <div class="label">
              Bankkort utstedt
            </div>
          </div>
        </div>
    </body>
</html>
