import { Component } from '@angular/core';
import { BackendService } from '../backend.service';

@Component({
  selector: 'app-signature',
  templateUrl: './signature.component.html',
  styleUrls: ['./signature.component.scss']
})
export class SignatureComponent {
  
    public text: string ="";
    public signature: string = "";
    public messaggio: string="";
  
    constructor(private backendService: BackendService) { }
  
    ngOnInit(): void {
    }
  
    public request(): void {
      this.backendService.request(this.text, this.signature).subscribe((response) => {
        this.messaggio = response;
      });
    }
 
  
}
