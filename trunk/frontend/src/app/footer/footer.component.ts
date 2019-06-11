import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  title = 'www.busyqa.ca | 456.234.4567';
  constructor() { }

  ngOnInit() {
  }

}
