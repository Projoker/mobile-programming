//
//  ViewController.swift
//  Lab1
//
//  Created by Bakulin Nikita Alekseevich on 11.01.2023.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var myLabel: UILabel!
    
    @IBOutlet weak var myButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


    @IBAction func myButtonClicked(_ sender: Any) {
        let currentDate = Date()
        
        let dateFormatter = DateFormatter()
        dateFormatter.dateStyle = .medium
        dateFormatter.timeStyle = .medium
        dateFormatter.locale = Locale(identifier: "ru_RU")
        
        myLabel.text = dateFormatter.string(from: currentDate)
    }
}

